package org.cmpa.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.logging.Logger;

import org.cmpa.model.Item;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a REST API class
 */
@RestController
public class ItemController {
    private static final Logger lgr = Logger.getLogger(ItemController.class.getName());

    private List<Item> items = new ArrayList<Item>(Arrays.asList(
          new Item( "001002003", "First item")
        , new Item( "002003004", "Second item")
        , new Item( "003004005", "Third item")
    ));

    private void traceLine( String msg ) {
        lgr.info( msg );
    }

    /** 
     * This echo API can be called w/o auth to test connectivity
     * */
    @GetMapping("/echo")
    public Item echoTestRaw() {
        traceLine( "echoTest" );
        return items.get(0);
    }
    /**
     * This echo API must be called with AzureAD OAuth token, but doesn't
     * query the database
     */
    @GetMapping("/api/echo")
    public Item echoTest() {
        traceLine( "echoTest" );
        return items.get(0);
    }

    /**
     * The below APIs must be called with AzureAD OAuth token
     * and queries the database
     */
    @GetMapping("/api/items")
    public List<Item> listItems() {
        traceLine( "listItems" );
        return items;
    }

    @GetMapping("/api/items/{id}")
    public Item getItem(@PathVariable String id, HttpServletResponse res) {
        traceLine( "getItem" );
        Item item = null;
        for( int n = 0; n < items.size(); n++ ) {
            if ( items.get(n).getItemId().equals(id) ) {
                item = items.get(n);
                break;
            }
        }
        if ( item == null ){
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);            
        }
        return item;
    }

    /**
     * If you should make APIs access dependant on group membership
     * this is how you would do it
     */
    //@PreAuthorize("hasRole('ROLE_group1')")
    //@RequestMapping(value = "/api/items", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    
}

