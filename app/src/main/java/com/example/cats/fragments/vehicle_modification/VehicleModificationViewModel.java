package com.example.cats.fragments.vehicle_modification;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class VehicleModificationViewModel {

    MutableLiveData<ItemDetailData> itemDetailData = new MutableLiveData<>(null);

    public LiveData<ItemDetailData> getItemDetailData() {
        return itemDetailData;
    }

    public void setItemDetailData(ItemDetailData itemDetailData) {
        this.itemDetailData.setValue(itemDetailData);
    }
}
