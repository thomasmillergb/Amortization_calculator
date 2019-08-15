package thomasmillergb.service

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import thomasmillergb.model.Lender
import thomasmillergb.model.LenderAmountPair

class ChepestLendersServiceTest : StringSpec({

    val underTest_ = ChepestLendersService()
    "Should bring back the 2 cheapest providers" {
        val lenders = listOf(
                Lender("Test1", 1.0, 100.0),
                Lender("Test2", 0.5, 50.0),
                Lender("Test3", 0.5, 50.0)
        )
        val result = underTest_.getLenderToBorrowFrom(100.0, lenders)

        result shouldHaveSize 2
        result shouldBe listOf(
                LenderAmountPair(lenders[1], 50.0),
                LenderAmountPair(lenders[2], 50.0)
        )

    }

    "Should bring back the one providers as is cheapest"{
        val lenders = listOf(
                Lender("Test1", 0.1, 100.0),
                Lender("Test2", 0.5, 50.0),
                Lender("Test3", 0.5, 50.0)
        )
        val result = underTest_.getLenderToBorrowFrom(100.0, lenders)

        result shouldHaveSize 1
        result shouldBe listOf(
                LenderAmountPair(lenders[0], 100.0)
        )

    }

    "Should bring back the two cheapest providers however lender 2 should only be borrowing a partial amount"{
        val lenders = listOf(
                Lender("Test1", 1.0, 100.0),
                Lender("Test2", 0.5, 75.0),
                Lender("Test3", 0.5, 50.0)
        )
        val result = underTest_.getLenderToBorrowFrom(100.0, lenders)

        result shouldHaveSize 2
        result shouldBe listOf(
                LenderAmountPair(lenders[1], 75.0),
                LenderAmountPair(lenders[2], 25.0)
        )

    }

    "Should throw runtime expection if there is not enough money in the lender pool"{

        val lenders = listOf(
                Lender("Test1", 1.0, 100.0),
                Lender("Test2", 0.5, 75.0),
                Lender("Test3", 0.5, 50.0)
        )
        val ex = shouldThrow<RuntimeException> { underTest_.getLenderToBorrowFrom(10000.0, lenders) }
        ex.message shouldBe "The current Lender pool do not offer enough money"

    }



})