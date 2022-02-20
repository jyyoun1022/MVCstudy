package hello.itemservice.domain;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA",10000,10);

        //when
        Item save = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findByID(item.getId());

        assertThat(findItem).isEqualTo(save);
    }

    @Test
    void findAll(){
        //given
        Item item1 = new Item("itemA",20000,15);
        Item item2 = new Item("itemB",10000,10);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);

    }

    @Test
    void updateItem(){
        //given
        Item item = new Item("itemA",10000,10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateparam = new Item("itemB", 20000, 50);
        itemRepository.update(itemId,updateparam);


        //then
        Item findItem = itemRepository.findByID(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateparam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateparam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateparam.getQuantity());
    }
}
