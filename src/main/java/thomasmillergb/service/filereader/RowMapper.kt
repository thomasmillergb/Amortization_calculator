package thomasmillergb.service.filereader

import thomasmillergb.model.Lender

class RowMapper {

    fun mapRow(raw: String): Lender? {

        val row = raw.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (row.size != 3) throw RuntimeException("Invalid row, not enough cols")
        return try {
            Lender(row[0], row[1].toDouble(), row[2].toDouble())
        }
        catch(e: Exception) {
            null
        }

    }

}
