package com.bluedelivery.shop.shop;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateClosingDaysRequest {
    @NotNull
    private Boolean legalHolidays;
    @Size(min = 0, max = 15)
    private List<RegularClosingParam> regularClosing;
    @Size(min = 0, max = 15)
    private List<TemporaryClosingParam> temporaryClosing;
    
    public UpdateClosingDaysRequest(Boolean legalHolidays,
                                    List<RegularClosingParam> regularClosing,
                                    List<TemporaryClosingParam> temporaryClosing) {
        this.legalHolidays = legalHolidays;
        this.regularClosing = regularClosing;
        this.temporaryClosing = temporaryClosing;
    }
    
    public Boolean getLegalHolidays() {
        return legalHolidays;
    }
    
    public List<RegularClosingParam> getRegularClosing() {
        return regularClosing;
    }
    
    public List<TemporaryClosingParam> getTemporaryClosing() {
        return temporaryClosing;
    }
}
