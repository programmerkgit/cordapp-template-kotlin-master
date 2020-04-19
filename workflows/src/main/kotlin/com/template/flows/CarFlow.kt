package com.template.flows

import co.paralleluniverse.fibers.Suspendable
import com.template.contracts.CarContract
import com.template.states.CarState
import net.corda.core.contracts.Command
import net.corda.core.contracts.UniqueIdentifier
import net.corda.core.flows.*
import net.corda.core.identity.Party
import net.corda.core.transactions.SignedTransaction
import net.corda.core.transactions.TransactionBuilder
import net.corda.core.utilities.ProgressTracker


/**
 * Flow has initiator and Responder.
 * */
// *********
// * Flows *
// *********
@InitiatingFlow
@StartableByRPC
class CarIssueInitiator(
        val owningBank: Party,
        val holdingDealer: Party,
        val manufacturer: Party,
        val vin: String,
        val licensePlateNumber: String,
        val make: String,
        val model: String,
        val dealershipLocation: String
) : FlowLogic<SignedTransaction>() {
    override val progressTracker = ProgressTracker()

    /**
     * CarIssueInitiator is Car Issue command Transaction Builder.
     * Call should return T of FlowLogic<T>
     * */
    @Suspendable
    override fun call(): SignedTransaction {
        /**
         * The networkMapCache contains information about the nodes and notaries inside the network.
         * Transaction specify which notary to use.
         * */
        val notary = serviceHub.networkMapCache.notaryIdentities.first()

        /**
         * command to send by transaction.
         * Command argument is Command and list of party keys.
         * */
        val command = Command(CarContract.Commands.Issue(), listOf(owningBank, holdingDealer, manufacturer).map { it.owningKey })
        /* State To be created By Issue*/
        val carState = CarState(owningBank, holdingDealer, manufacturer, vin, licensePlateNumber, make, model, dealershipLocation, UniqueIdentifier())
        /* Transaction builder to be sent*/
        val txBuilder = TransactionBuilder(notary).addOutputState(carState).addCommand(command)
        /* to be verified before sent. Notarize?? */
        txBuilder.verify(serviceHub)
        /* first sign by notary or transaction issuer?? */
        val tx = serviceHub.signInitialTransaction(txBuilder)
        // Initiator flow logic goes here.
        /**
         * To finish the initiator’s call() function, other parties must sign the transaction. Add the following code to send the transaction to the other relevant parties:
         * kotlin
         * val sessions = (carState.participants - ourIdentity).map { initiateFlow(it as Party) }
         * val stx = subFlow(CollectSignaturesFlow(tx, sessions))
         * return subFlow(FinalityFlow(stx, sessions))
         * */

        /**
         * Collect signs using subflow.
         * Sub flow returns sign if verified by all parties
         * */
        return tx
    }
}

@InitiatedBy(Initiator::class)
class CarClassResponder(val counterpartySession: FlowSession) : FlowLogic<SignedTransaction>() {
    @Suspendable
    override fun call(): SignedTransaction {
        // Responder flow logic goes here.
    }
}
