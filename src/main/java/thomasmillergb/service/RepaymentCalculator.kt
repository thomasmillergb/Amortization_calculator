package thomasmillergb.service

import java.math.RoundingMode
import kotlin.math.pow


class RepaymentCalculator() {
    private val monthsInYear = 12

    /*
    * formuals is A = p x i / (1-(1 + i)^-n )
    *
    * P = amount of principal, net of initial payments, meaning "subtract any down-payments"
    * i = periodic interest rate
    * n = total number of payments
    */
    fun calcMonthlyRepayment(loanAmount: Double, rate: Double, numberOfMonths: Int): Double {

        return if (rate == 0.0) {
            loanAmount / numberOfMonths
        } else {
            val p = loanAmount
            val i = rate / monthsInYear
            val n = numberOfMonths
            return (p * i) / (1 - (1 + i).pow(-n))
//            return (p * i) / (1 - (1 / (1 + i).pow(n)))
        }

    }
}