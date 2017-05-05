package stock.dao;

/**
 * Created by Aleksey on 05.05.2017.
 */
public interface StockDAO<Stock>{
    void addComponent();

    void refactor(int id);

    void delete(int id);

    void showAll();


}
