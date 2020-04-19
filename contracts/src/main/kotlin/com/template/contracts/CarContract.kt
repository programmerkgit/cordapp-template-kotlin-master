package com.template.contracts

import com.template.states.CarState
import net.corda.core.contracts.CommandData
import net.corda.core.contracts.Contract
import net.corda.core.contracts.requireSingleCommand
import net.corda.core.contracts.requireThat
import net.corda.core.transactions.LedgerTransaction

// ************
// * Contract *
// ************
class CarContract : Contract {
    companion object {
        // Used to identify our contract when building a transaction.
        val ID = CarContract::class.qualifiedName!!
    }

    // A transaction is valid if the verify() function of the contract of all the transaction's input and output states
    // does not throw an exception.
    // verify is a rule commands of transaction should satisfy
    override fun verify(tx: LedgerTransaction) {
        /* verify have tx argument */
        /* transaction should have commands interface defined bellow. */
        val command = tx.commands.requireSingleCommand<Commands>().value
        when (command) {
            /* When Issue Command verification  */
            is Commands.Issue -> requireThat {
                "There should be no input state" using (tx.inputs.isEmpty())
                "There should be one input state" using (tx.outputs.size == 1)
                "The output state must be of type CarState" using (tx.outputs[0].data is CarState)
                val outputState = tx.outputs[0].data as CarState
                "The licensePlateNumber must be seven characters long" using (outputState.licensePlateNumber.length == 7)
            }
            is Commands.Change -> requireThat {
                /* some other rules */
            }
        }
    }

    // Used to indicate the transaction's intent.
    interface Commands : CommandData {
        class Issue : Commands
        class Change : Commands
    }
}