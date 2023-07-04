package com.example.untiled1.global.base;

import com.example.untiled1.global.constants.ConstantEx;
import com.example.untiled1.global.constants.ESort;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lowagie.text.BadElementException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.apache.commons.lang3.math.NumberUtils;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.Arrays;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class ConditionalBaseEo<T extends ConditionalBaseEo> {
    @JsonIgnore
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            notes = "PAGE"
    )
    @Transient
    @JsonIgnore
    private @Min(0L) @Max(2147483647L) Integer page = 0;
    @ApiModelProperty(
            notes = "SIZE"
    )
    @Transient
    @JsonIgnore
    private @Min(0L) @Max(2147483647L) Integer size = 20;
    @ApiModelProperty(
            notes = "PROPERTIES"
    )
    @Transient
    @JsonIgnore
    private String[] properties = new String[0];
    @ApiModelProperty(
            notes = "DIRECTION"
    )
    @Transient
    @JsonIgnore
    private ESort.Direction direction;
    @ApiModelProperty(
            notes = "TOTAL_ELEMENTS",
            hidden = true
    )
    @Column(
            name = "TOTAL_ELEMENTS"
    )
    @Transient
    @JsonIgnore
    private Long totalElements;

    public Integer getSize() {
        if (this.size != null && this.size > ConstantEx.REST_API_REQUEST_PARAM_SIZE_MAX_VALUE) {
            throw new BadElementException(String.format("Chỉ cho phép truy vấn dữ liệu dưới %s bản ghi.", ConstantEx.REST_API_REQUEST_PARAM_SIZE_MAX_VALUE));
        } else {
            return this.size;
        }
    }

    public ConditionalBaseEo(String[] properties) {
        this.direction = ESort.Direction.ASC;
        this.page = 0;
        this.size = 20;
        this.properties = properties;
        this.direction = ESort.Direction.ASC;
    }

    @JsonIgnore
    @ApiModelProperty(
            hidden = true
    )
    public String getNotifyWithKey() {
        throw new UnsupportedOperationException("Must implement the inherited abstract method getNotifyWithKey()");
    }

    @JsonIgnore
    @ApiModelProperty(
            hidden = true
    )
    public T cloneNotRef() {
        throw new UnsupportedOperationException("Must implement the inherited abstract method cloneNotRef()");
    }

    @JsonIgnore
    public T setConditionalBaseEo(Integer page, Integer size, String[] properties, ESort.Direction direction) {
        this.setPage(page);
        this.setSize(size);
        this.setProperties(properties);
        this.setDirection(direction);
        return (T) this;
    }

    public String[] getProperties() {
        if (this.properties != null) {
            this.properties = (String[]) Arrays.stream(this.properties).filter((obj) -> {
                return !NumberUtils.isDigits(obj);
            }).toArray((x$0) -> {
                return new String[x$0];
            });
        }

        return this.properties;
    }

    @JsonIgnore
    @ApiModelProperty(
            hidden = true
    )
    public Integer getPageBegin() {
        return this.getPage() * this.getSize() + 1;
    }

    @JsonIgnore
    @ApiModelProperty(
            hidden = true
    )
    public Integer getPageEnd() {
        return this.getPage() * this.getSize() + this.getSize();
    }

    public ConditionalBaseEo() {
        this.direction = ESort.Direction.ASC;
    }

    public ConditionalBaseEo(final Integer page, final Integer size, final String[] properties, final ESort.Direction direction, final Long totalElements) {
        this.direction = ESort.Direction.ASC;
        this.page = page;
        this.size = size;
        this.properties = properties;
        this.direction = direction;
        this.totalElements = totalElements;
    }

    public Integer getPage() {
        return this.page;
    }

    public ESort.Direction getDirection() {
        return this.direction;
    }

    public Long getTotalElements() {
        return this.totalElements;
    }

    @JsonIgnore
    public void setPage(final Integer page) {
        this.page = page;
    }

    @JsonIgnore
    public void setSize(final Integer size) {
        this.size = size;
    }

    @JsonIgnore
    public void setProperties(final String[] properties) {
        this.properties = properties;
    }

    @JsonIgnore
    public void setDirection(final ESort.Direction direction) {
        this.direction = direction;
    }

    @JsonIgnore
    public void setTotalElements(final Long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ConditionalBaseEo)) {
            return false;
        } else {
            ConditionalBaseEo<?> other = (ConditionalBaseEo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label63: {
                    Object this$page = this.getPage();
                    Object other$page = other.getPage();
                    if (this$page == null) {
                        if (other$page == null) {
                            break label63;
                        }
                    } else if (this$page.equals(other$page)) {
                        break label63;
                    }

                    return false;
                }

                Object this$size = this.getSize();
                Object other$size = other.getSize();
                if (this$size == null) {
                    if (other$size != null) {
                        return false;
                    }
                } else if (!this$size.equals(other$size)) {
                    return false;
                }

                Object this$totalElements = this.getTotalElements();
                Object other$totalElements = other.getTotalElements();
                if (this$totalElements == null) {
                    if (other$totalElements != null) {
                        return false;
                    }
                } else if (!this$totalElements.equals(other$totalElements)) {
                    return false;
                }

                if (!Arrays.deepEquals(this.getProperties(), other.getProperties())) {
                    return false;
                } else {
                    Object this$direction = this.getDirection();
                    Object other$direction = other.getDirection();
                    if (this$direction == null) {
                        if (other$direction != null) {
                            return false;
                        }
                    } else if (!this$direction.equals(other$direction)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ConditionalBaseEo;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $page = this.getPage();
        result = result * 59 + ($page == null ? 43 : $page.hashCode());
        Object $size = this.getSize();
        result = result * 59 + ($size == null ? 43 : $size.hashCode());
        Object $totalElements = this.getTotalElements();
        result = result * 59 + ($totalElements == null ? 43 : $totalElements.hashCode());
        result = result * 59 + Arrays.deepHashCode(this.getProperties());
        Object $direction = this.getDirection();
        result = result * 59 + ($direction == null ? 43 : $direction.hashCode());
        return result;
    }

    public String toString() {
        return "ConditionalBaseEo(page=" + this.getPage() + ", size=" + this.getSize() + ", properties=" + Arrays.deepToString(this.getProperties()) + ", direction=" + this.getDirection() + ", totalElements=" + this.getTotalElements() + ")";
    }
}

