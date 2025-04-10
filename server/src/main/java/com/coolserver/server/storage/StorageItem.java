package com.coolserver.server.storage;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="storage_items")
public class StorageItem {
    
    @Id
    @SequenceGenerator(name = "storage_item_sequence", sequenceName = "storage_item_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storage_item_sequence")
    private Long id;

    private String name;
    private LocalDateTime date;
    private long size;
    private String extension;

    private Long userId;

    public StorageItem(){
    }

    
    public StorageItem(String name, LocalDateTime date, long size, String extension, Long userId) {
        this.name = name;
        this.date = date;
        this.size = size;
        this.extension = extension;
        this.userId = userId;
    }

    public StorageItem(Long id, String name, LocalDateTime date, long size, String extension, Long userId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.size = size;
        this.extension = extension;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "StorageItem [id=" + id + ", name=" + name + ", date=" + date + ", size=" + size + ", extension="
                + extension + ", userId=" + userId + "]";
    }

}
