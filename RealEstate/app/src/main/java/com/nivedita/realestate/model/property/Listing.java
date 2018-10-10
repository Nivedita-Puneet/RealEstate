
package com.nivedita.realestate.model.property;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Listing {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("AgencyLogoUrl")
    @Expose
    private String agencyLogoUrl;
    @SerializedName("Area")
    @Expose
    private String area;
    @SerializedName("AuctionDate")
    @Expose
    private String auctionDate;
    @SerializedName("AvailableFrom")
    @Expose
    private Object availableFrom;
    @SerializedName("Bathrooms")
    @Expose
    private Integer bathrooms;
    @SerializedName("Bedrooms")
    @Expose
    private Integer bedrooms;
    @SerializedName("Carspaces")
    @Expose
    private Integer carspaces;
    @SerializedName("DateFirstListed")
    @Expose
    private String dateFirstListed;
    @SerializedName("DateUpdated")
    @Expose
    private String dateUpdated;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("DisplayPrice")
    @Expose
    private String displayPrice;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("Location")
    @Expose
    private Location location;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("ImageUrls")
    @Expose
    private List<String> imageUrls = null;
    @SerializedName("is_premium")
    @Expose
    private Integer isPremium;
    @SerializedName("IsPriority")
    @Expose
    private Integer isPriority;
    @SerializedName("Latitude")
    @Expose
    private Float latitude;
    @SerializedName("ListingPrice")
    @Expose
    private Object listingPrice;
    @SerializedName("ListingStatistics")
    @Expose
    private Object listingStatistics;
    @SerializedName("ListingType")
    @Expose
    private String listingType;
    @SerializedName("ListingTypeString")
    @Expose
    private String listingTypeString;
    @SerializedName("Longitude")
    @Expose
    private Float longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Listing withId(String id) {
        this.id = id;
        return this;
    }

    public String getAgencyLogoUrl() {
        return agencyLogoUrl;
    }

    public void setAgencyLogoUrl(String agencyLogoUrl) {
        this.agencyLogoUrl = agencyLogoUrl;
    }

    public Listing withAgencyLogoUrl(String agencyLogoUrl) {
        this.agencyLogoUrl = agencyLogoUrl;
        return this;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Listing withArea(String area) {
        this.area = area;
        return this;
    }

    public String getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(String auctionDate) {
        this.auctionDate = auctionDate;
    }

    public Listing withAuctionDate(String auctionDate) {
        this.auctionDate = auctionDate;
        return this;
    }

    public Object getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Object availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Listing withAvailableFrom(Object availableFrom) {
        this.availableFrom = availableFrom;
        return this;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Listing withBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
        return this;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Listing withBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
        return this;
    }

    public Integer getCarspaces() {
        return carspaces;
    }

    public void setCarspaces(Integer carspaces) {
        this.carspaces = carspaces;
    }

    public Listing withCarspaces(Integer carspaces) {
        this.carspaces = carspaces;
        return this;
    }

    public String getDateFirstListed() {
        return dateFirstListed;
    }

    public void setDateFirstListed(String dateFirstListed) {
        this.dateFirstListed = dateFirstListed;
    }

    public Listing withDateFirstListed(String dateFirstListed) {
        this.dateFirstListed = dateFirstListed;
        return this;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Listing withDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Listing withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    public Listing withDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Listing withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Listing withLocation(Location location) {
        this.location = location;
        return this;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Listing withOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Listing withImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

    public Integer getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(Integer isPremium) {
        this.isPremium = isPremium;
    }

    public Listing withIsPremium(Integer isPremium) {
        this.isPremium = isPremium;
        return this;
    }

    public Integer getIsPriority() {
        return isPriority;
    }

    public void setIsPriority(Integer isPriority) {
        this.isPriority = isPriority;
    }

    public Listing withIsPriority(Integer isPriority) {
        this.isPriority = isPriority;
        return this;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Listing withLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public Object getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(Object listingPrice) {
        this.listingPrice = listingPrice;
    }

    public Listing withListingPrice(Object listingPrice) {
        this.listingPrice = listingPrice;
        return this;
    }

    public Object getListingStatistics() {
        return listingStatistics;
    }

    public void setListingStatistics(Object listingStatistics) {
        this.listingStatistics = listingStatistics;
    }

    public Listing withListingStatistics(Object listingStatistics) {
        this.listingStatistics = listingStatistics;
        return this;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public Listing withListingType(String listingType) {
        this.listingType = listingType;
        return this;
    }

    public String getListingTypeString() {
        return listingTypeString;
    }

    public void setListingTypeString(String listingTypeString) {
        this.listingTypeString = listingTypeString;
    }

    public Listing withListingTypeString(String listingTypeString) {
        this.listingTypeString = listingTypeString;
        return this;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Listing withLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

}
