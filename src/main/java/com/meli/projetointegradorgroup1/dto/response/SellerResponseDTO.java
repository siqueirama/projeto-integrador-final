package com.meli.projetointegradorgroup1.dto.response;
import com.meli.projetointegradorgroup1.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author Hugo Victor
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerResponseDTO {

    private Long id;
    private String name;
    private String cpf;
    private String email;

public SellerResponseDTO(Seller seller){
    this.id= seller.getId();
    this.name = seller.getName();
    this.cpf = seller.getCpf();
    this.email = seller.getEmail();
    }

}
