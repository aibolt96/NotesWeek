package com.devmountain.noteApp.dtos;

import com.devmountain.noteApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesDto implements Serializable {
    private Long id;
    private String body;
    private UserDto userDto;

    public NotesDto(Notes notes){
        if (notes.getId() != null){
            this.id = notes.getId();
        }
        if (notes.getBody() != null){
            this.body = notes.getBody();
        }
    }
}
