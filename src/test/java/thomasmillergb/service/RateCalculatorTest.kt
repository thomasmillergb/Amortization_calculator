package thomasmillergb.service

import io.kotlintest.matchers.doubles.shouldBeExactly
import io.kotlintest.specs.StringSpec
import thomasmillergb.model.Lender
import thomasmillergb.model.LenderAmountPair

class RateCalculatorTest : StringSpec({

    val underTest = RateCalculator()
    "New rates should be the same" {

        val lenders = listOf(
                LenderAmountPair(Lender("lender1", 0.5, 50.0), 50.0),
                LenderAmountPair(Lender("lender2", 0.5, 50.0), 50.0)
        )
        val result = underTest.calcNewRate(100.0, lenders)
        result shouldBeExactly 0.5
    }

    "Where both lenders borrow the same but lender 1 has double the rate of lender 2, new rates should be in the middle" {

        val lenders = listOf(
                LenderAmountPair(Lender("lender1", 1.0, 50.0), 50.0),
                LenderAmountPair(Lender("lender2", 0.5, 50.0), 50.0)
        )
        val result = underTest.calcNewRate(100.0, lenders)
        result shouldBeExactly 0.75
    }
    "Where both lenders have the same rate but lender 1 is twice the amount should have same rate" {

        val lenders = listOf(
                LenderAmountPair(Lender("lender1", 0.5, 100.0), 100.0),
                LenderAmountPair(Lender("lender2", 0.5, 50.0), 50.0)
        )
        val result = underTest.calcNewRate(150.0, lenders)
        result shouldBeExactly 0.5
    }
    "Where both lenders have the same rate but lender 3 has twice the rate" {

        val lenders = listOf(
                LenderAmountPair(Lender("lender1", 0.5, 50.0), 50.0),
                LenderAmountPair(Lender("lender2", 0.5, 50.0), 50.0),
                LenderAmountPair(Lender("lender3", 1.0, 50.0), 50.0)
        )
        val result = underTest.calcNewRate(150.0, lenders)
        result shouldBeExactly 0.6666666666666666
    }



})

