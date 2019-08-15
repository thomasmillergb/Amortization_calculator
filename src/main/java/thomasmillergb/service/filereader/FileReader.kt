package thomasmillergb.service.filereader

import thomasmillergb.model.Lender
import java.io.File


class FileReader(private val rowMapper: RowMapper) {


    fun readFile(file: File): List<Lender> {
        return file.readLines().mapNotNull(rowMapper::mapRow)
    }

}
