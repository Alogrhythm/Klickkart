package com.shop.app.shared.shoppingcontext.retail;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.shop.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.shop.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_Item_M")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "5", comments = "Item", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Item.DefaultFinders", query = "select e from Item e where e.systemInfo.activeStatus=1 and e.itemName LIKE :itemName"), @javax.persistence.NamedQuery(name = "Item.findByProductId", query = "select e from Item e where e.systemInfo.activeStatus=1 and e.productId=:productId"), @javax.persistence.NamedQuery(name = "Item.findById", query = "select e from Item e where e.systemInfo.activeStatus=1 and e.itemId =:itemId") })
public class Item implements Serializable, CommonEntityInterface, Comparator<Item> {

    @Column(name = "itemName")
    @JsonProperty("itemName")
    @NotNull
    @Size(min = 1, max = 256)
    private String itemName;

    @Column(name = "itemImg")
    @JsonProperty("itemImg")
    @NotNull
    @Size(min = 1, max = 256)
    private String itemImg;

    @Column(name = "itemStock")
    @JsonProperty("itemStock")
    @NotNull
    @Min(0)
    @Max(2147483647)
    private Integer itemStock;

    @Column(name = "itemPrice")
    @JsonProperty("itemPrice")
    @NotNull
    @Min(0)
    @Max(99999999999L)
    private Double itemPrice;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "itemId")
    @JsonProperty("itemId")
    @GeneratedValue(generator = "UUIDGenerator")
    private String itemId;

    @Column(name = "productId")
    @JsonProperty("productId")
    private String productId;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String _itemName) {
        if (_itemName != null) {
            this.itemName = _itemName;
        }
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String _itemImg) {
        if (_itemImg != null) {
            this.itemImg = _itemImg;
        }
    }

    public Integer getItemStock() {
        return itemStock;
    }

    public void setItemStock(Integer _itemStock) {
        if (_itemStock != null) {
            this.itemStock = _itemStock;
        }
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double _itemPrice) {
        if (_itemPrice != null) {
            this.itemPrice = _itemPrice;
        }
    }

    public String getPrimaryKey() {
        return itemId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String _itemId) {
        this.itemId = _itemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String _productId) {
        this.productId = _productId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
    }

    public void setPrimaryDisplay(String _primaryDisplay) {
        this.primaryDisplay = _primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo _systemInfo) {
        this.systemInfo = _systemInfo;
    }

    @JsonIgnore
    public boolean isHardDelete() {
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            return true;
        } else {
            return false;
        }
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.entityValidator = _validateFactory;
    }

    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        System.out.println("Setting logged in user info for " + recordType);
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    @Override
    public int compare(Item object1, Item object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((itemName == null ? " " : itemName) + ",");
        sb.append((itemStock == null ? " " : itemStock) + ",");
        sb.append((itemPrice == null ? " " : itemPrice));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (itemId == null) {
            return super.hashCode();
        } else {
            return itemId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.shop.app.shared.shoppingcontext.retail.Item other = (com.shop.app.shared.shoppingcontext.retail.Item) obj;
            if (itemId == null) {
                return false;
            } else if (!itemId.equals(other.itemId)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }
}
