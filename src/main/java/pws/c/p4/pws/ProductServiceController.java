/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.c.p4.pws;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pws.c.p4.Product;

/**
 *
 * @author acer
 */
@RestController
public class ProductServiceController {
    
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
       Product honey = new Product();
       honey.setId("1");
       honey.setName("Madu");
       honey.setJumlah("2");
       honey.setHarga("Rp. 45.000");
       productRepo.put(honey.getId(), honey);

       Product almond = new Product();
       almond.setId("2");
       almond.setName("Kacang Almond");
       almond.setJumlah("2 ");
       honey.setHarga("Rp. 105.000");
       productRepo.put(almond.getId(), almond);
       
       Product bakso = new Product();
       bakso.setId("3");
       bakso.setName("Bakso");
       bakso.setJumlah("1");
       bakso.setHarga("Rp. 50.000");
       productRepo.put(bakso.getId(), bakso);
    }
    
    
    //menambahkan method delete dan menampilkan respon ketika melakukan delete
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
         
       productRepo.remove(id);
       return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
    }
    
    //menambahkan metohd put untuk mengedit data 
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Product product) { 
        
        //menambahkan fungsi if apabila id tidak ada akan muncul message "Data not found"
       if(!productRepo.containsKey(id)){ 
     
          
           return new ResponseEntity<>("Data not found", HttpStatus.INTERNAL_SERVER_ERROR);
       }
       //menambahkan fungsi else apabila id ada  akan muncul message "Product is updated successsfully"
       else{
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
    
           
       }
       
    }
    
    
    //menambahkan method POST untuk menambah data
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Product product) {
       
       //menambahkan fungsi else apabila akan membuat data id sama akan muncul message "ID Cannot be the same"
       if(productRepo.containsKey(product.getId())){
     
          
           return new ResponseEntity<>("ID Cannot be the same", HttpStatus.CREATED);
          
      }
        //menambahkan fungsi else apabila id ada akan muncul message "Product is created successsfully"
       productRepo.put(product.getId(), product);
       return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }
    
    
    //menambahkan method GET untuk menampilkan data dari Hashmap
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
       return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    
    



   
}
