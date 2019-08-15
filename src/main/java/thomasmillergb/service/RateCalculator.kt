package thomasmillergb.service

import thomasmillergb.model.LenderAmountPair


class RateCalculator() {
    private val monthsInYear = 12

    fun calcNewRate(loanAmount: Double, lenders: List<LenderAmountPair>): Double {
        return lenders.sumByDouble{(it.second / loanAmount) * it.first.rate}
    }

}