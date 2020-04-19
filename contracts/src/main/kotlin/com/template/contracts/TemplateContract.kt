package com.template.contracts

import net.corda.core.contracts.CommandData
import net.corda.core.contracts.Contract
import net.corda.core.transactions.LedgerTransaction

// ************
// * Contract *
// ************
class TemplateContract : Contract {
    companion object {
        // Used to identify our contract when building a transaction.
        const val ID = "com.template.contracts.TemplateContract"
    }

    // A transaction is valid if the verify() function of the contract of all the transaction's input and output states
    // does not throw an exception.
    // verify is a rule commands of transaction should satisfy
    override fun verify(tx: LedgerTransaction) {
        /* verify have tx argument */
        /* transaction should have commands interface defined bellow. */
        val command = tx.commands.requireSingleCommand<Commands>().value
        when (command) {
            is Commands.Issue -> requireThat {
                "There should be no input state" using (tx.inputs.isEmpty())
            }
        }
    }

    // Used to indicate the transaction's intent.
    interface Commands : CommandData {
        class Issue : Commands
    }
}
