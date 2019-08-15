package thomasmillergb.service

import thomasmillergb.model.Lender
import java.io.FileReader
import java.math.RoundingMode

class QuoteService(
                   private val rateCalculator: RateCalculator,
                   private val repaymentCalculator: RepaymentCalculator,
                   private val chepestLendersService: ChepestLendersService) {

    private val REPAYMENT_PERIOD_MONTHS = 36


    fun getQuote(lenders: List<Lender>, requestedAmount: Double) {

        if(requestedAmount < 1000 || requestedAmount > 15000){
            throw RuntimeException("The requested amout must be between 1000 and 15000")
        }
        if((requestedAmount /100).rem(1) > 0 ){
            throw RuntimeException("The request amount must be in 100s")
        }
        val lendersToBorrow = chepestLendersService.getLenderToBorrowFrom(requestedAmount, lenders)
        val newRate = rateCalculator.calcNewRate(requestedAmount, lendersToBorrow)
        val repayment = repaymentCalculator.calcMonthlyRepayment(requestedAmount, newRate, REPAYMENT_PERIOD_MONTHS)
        val totalRepayment = repayment * REPAYMENT_PERIOD_MONTHS
        println("Requested amount: £${format(requestedAmount, 2)}")
        println("Annual Interest Rate: £${format(newRate * 100, 1)}%")
        println("Monthly repayment: £${format(repayment, 2)}")
        println("Total repayment: £${format(totalRepayment, 2)}")

    }

    fun format(vaule: Double, places: Int): String {
        return vaule.toBigDecimal().setScale(places, RoundingMode.UP).toString()
    }


}