package thomasmillergb.service

import io.kotlintest.matchers.doubles.shouldBeExactly
import io.kotlintest.matchers.doubles.shouldBeGreaterThan
import io.kotlintest.matchers.doubles.shouldBeLessThan
import io.kotlintest.specs.StringSpec
import kotlin.math.round

class RepaymentCalculatorTest : StringSpec({

    val underTest = RepaymentCalculator()
    "Repayment should be exactly loan amount / 12 of rate is 0 with a 1 year repayment"{
        val loanAmount = 120.0
        val months = 12
        val result = underTest.calcMonthlyRepayment(loanAmount, 0.00, months)
        result shouldBeExactly  loanAmount / months
    }

    "Repayment should be greater then loan amount / number of months"{
        val loanAmount = 120.0
        val months = 12
        val result = underTest.calcMonthlyRepayment(loanAmount, 0.01, months)
        result shouldBeGreaterThan loanAmount / months
    }
    "Repayment with a loan amount of 120 and 1 year repayment"{
        val loanAmount = 120.0
        val months = 12
        val result = underTest.calcMonthlyRepayment(loanAmount, 0.01, months)
        result shouldBeGreaterThan 10.0
        result shouldBeLessThan  11.0
        (round(result * 100) / 100) shouldBeExactly 10.05
    }
    "Repayment with a loan amount of 360 and 3 year repayment"{
        val loanAmount = 360.0
        val months = 36
        val result = underTest.calcMonthlyRepayment(loanAmount, 0.01, months)
        result shouldBeGreaterThan 10.0
        result shouldBeLessThan  11.0
        (round(result * 100) / 100) shouldBeExactly 10.15
    }

    "Repayment of 30.78 with rate of 7.0 with amount £1000"{
        val loanAmount = 1000.0
        val months = 36
        val result = underTest.calcMonthlyRepayment(loanAmount, 0.07, months)
        (round(result * 100) / 100) shouldBeExactly 30.88
    }



})