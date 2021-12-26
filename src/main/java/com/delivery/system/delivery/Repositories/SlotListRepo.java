package com.delivery.system.delivery.Repositories;

import com.delivery.system.delivery.Models.Slots;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SlotListRepo {
    public List<Slots> slotsList = new ArrayList<>();
}
