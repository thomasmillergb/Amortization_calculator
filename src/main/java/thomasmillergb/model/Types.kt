package thomasmillergb.model

typealias LenderAmountPair = Pair<Lender, Double>
data class Lender(val name:String, val rate:Double, val available: Double)
data class Quote(val amount:Int, val rate: Double, val monthlyRepayment:Double, val totalRepayment: Double)
data class Request(val amount: Int)
data class Tenor(val months: Int)