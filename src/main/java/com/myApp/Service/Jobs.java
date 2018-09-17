package com.myApp.Service;


import com.myApp.Dto.JobsDtoRequest;

import javax.persistence.*;
import java.util.List;

@Entity
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<String> urls;

    protected Jobs(){

    }

    public Jobs(JobsDtoRequest jobDto){
        super();
        urls = jobDto.getUrls();
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public long getId() {
        return id;
    }

    public List<String> getUrls(){
        return urls;
    }
}
