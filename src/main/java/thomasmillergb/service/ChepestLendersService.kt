package thomasmillergb.service

import thomasmillergb.model.Lender
import thomasmillergb.model.LenderAmountPair

class ChepestLendersService() {


    fun getLenderToBorrowFrom(requestedAmount: Double, lenders: List<Lender>): List<LenderAmountPair> {
        if(lenders.sumByDouble { it.available } < requestedAmount) throw RuntimeException("The current Lender pool do not offer enough money")
        val lendersToBorrow = mutableListOf<Pair<Lender, Double>>()
        var leftToBorrow = requestedAmount
        for (lender in lenders.sortedBy { it.rate }) {
            if (leftToBorrow == 0.0) {
                return lendersToBorrow
            } else {
                val amountToBorrow = getAmountToBorrow(leftToBorrow, lender)
                leftToBorrow -= amountToBorrow
                lendersToBorrow.add(Pair(lender, amountToBorrow))

            }
        }
        return lendersToBorrow
    }

    private fun getAmountToBorrow(leftToBorrow: Double, lender: Lender): Double {
        return if (lender.available > leftToBorrow) {
            leftToBorrow
        } else {
            lender.available
        }
    }
}