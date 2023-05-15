package it.develhope.shoppyz.cart;

import it.develhope.shoppyz.account.Account;
import it.develhope.shoppyz.order.Order;
import it.develhope.shoppyz.product.Product;
import it.develhope.shoppyz.order.OrderRepository;
import it.develhope.shoppyz.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository ;
    @Autowired
    OrderRepository orderRepository;


    @Override
    public List<Cart> findAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> getCart(int id) {
        return cartRepository.findById(id);
    }

    @Override
    public void updateCart(Cart cart) {
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public void addProductToCart(Product product, Account account, int qty) {

    }

    @Override
    public void addProductToCart(Cart cart, Product product, int quantity) {

        Cart cartProv= cart;
        product.setQuantity(quantity);
        cartProv.getProductsInCart().add(product);

    }

    @Override
    public Cart findById(int id) {
        return  cartRepository.findById(id).orElse(null);
    }

    @Override
    public void removeCart(Integer id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public void removedProduct(Product product, int quantity) {

    }

    @Override
    public List<Product> removedProduct(List<Product> list, int id_prod) {

        //logica per individuare il prodotto da eliminare (key)
        //rimuovi l'elemento della lista ritornandola


        return null; //ritorni la lista
    }


    public void makeOrder(Cart cart){
        Order provOrd= new Order();
        provOrd.setCreateDate(new java.util.Date());
        provOrd.setIsgift(cart.getIsgift());
        provOrd.setTrackingNumber(randomTrackingNumberGenerator());
        provOrd.setBuyerAccount(cart.getAccount());
        provOrd.setProductsList(cart.getProductsInCart());
        orderRepository.saveAndFlush(provOrd);
    }

    /** logica per autogenerazione trackingnumb */

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String randomTrackingNumberGenerator(){

        StringBuilder buffer = new StringBuilder(12);
        buffer.append("IT");
        for(int i = 0; i< 10; i++){
            int r= getRandomNumber(1,2);
            if(r==1)
            buffer.append((char) getRandomNumber(48,57));
            if(r==2)
            buffer.append((char) getRandomNumber(65,90));
        }
        String returnedString= buffer.toString();
        return returnedString;
    }





}