package thomasmillergb;

import thomasmillergb.model.Lender;
import thomasmillergb.service.ChepestLendersService;
import thomasmillergb.service.QuoteService;
import thomasmillergb.service.RateCalculator;
import thomasmillergb.service.RepaymentCalculator;
import thomasmillergb.service.filereader.FileReader;
import thomasmillergb.service.filereader.RowMapper;

import java.io.File;
import java.util.List;

public class App {

    public static void main(String[] args) {
        QuoteService quoteService = new QuoteService(new RateCalculator(), new RepaymentCalculator(), new ChepestLendersService());
        List<Lender> lenders = new FileReader(new RowMapper()).readFile(new File(args[0]));
        quoteService.getQuote(lenders, Double.parseDouble(args[1]));
    }
}
