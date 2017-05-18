package stock.dao;

/**
 * Created by Aleksey on 18.05.2017.
 */
public interface StockHistoryDAO {

    void addEntry();

    void refactor(int id);

    void delete(int id);

    void showAll();
}
