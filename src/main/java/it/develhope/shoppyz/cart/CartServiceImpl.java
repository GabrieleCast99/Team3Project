package it.develhope.shoppyz.cart;

import it.develhope.shoppyz.relationProdCart.Cart_Product;
import it.develhope.shoppyz.relationProdCart.Cart_ProductRepository;
import it.develhope.shoppyz.product.Product;
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
    Cart_ProductRepository cartProductRepository;


    @Override
    public List<Cart> findAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> getCart(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void updateCart(Cart cart) {
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public Cart findById(Long id) {
        return  cartRepository.findById(id).orElse(null);
    }

    @Override
    public void removeCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart addProductToCart(Long accountid, Long productid) throws Exception {

        Cart cart=cartRepository.findById(accountid).orElseThrow(()->new Exception("Cart with id: "+accountid+" not found"));;
        Product product= productRepository.getReferenceById(productid);
        Cart_Product cartProduct= new Cart_Product();
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        cart.registrations.add(cartProduct);
        cartProductRepository.save(cartProduct);
        return cartRepository.save(cart);
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public void removedProduct(Product product, Long quantity) {

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
