package com.shop.app.shared.shoppingcontext.retail;
import com.athena.server.pluggable.interfaces.EntityValidatorInterface;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TemplateOfItem implements EntityValidatorInterface {

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> dtoValidator;

    @Min(1)
    @Max(65535)
    private String productDesc;

    @Min(1)
    @Max(65535)
    private String itemName;

    @Min(1)
    @Max(65535)
    private String itemImg;

    @Min(1)
    @Max(65535)
    private String itemId;

    @Min(-2147483648L)
    @Max(2147483647)
    private Integer itemPrice;

    @Min(-2147483648L)
    @Max(2147483647)
    private Integer qty;

    @Transient
    @JsonIgnore
    private boolean isDtoValidated = false;

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isDtoValidated;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String _productDesc) {
        this.productDesc = _productDesc;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String _itemName) {
        this.itemName = _itemName;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String _itemImg) {
        this.itemImg = _itemImg;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String _itemId) {
        this.itemId = _itemId;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer _itemPrice) {
        this.itemPrice = _itemPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer _qty) {
        this.qty = _qty;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.dtoValidator = _validateFactory;
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.dtoValidator != null) {
            isValid = this.dtoValidator.validateEntity(this);
            this.isDtoValidated = true;
        } else {
            throw new SecurityException();
        }
        return isValid;
    }
}
