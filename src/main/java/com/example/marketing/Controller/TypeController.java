package com.example.marketing.Controller;

import com.example.marketing.Model.Type;
import com.example.marketing.Service.TypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/type")
@RequiredArgsConstructor

public class TypeController {
    private final TypeService typeService;
    @GetMapping("/getAll")
    public ResponseEntity getAllType(){
        return ResponseEntity.ok().body(typeService.getAllTypes());
    }
    @PostMapping("/add/{platformId}")
    public ResponseEntity addType(@PathVariable Integer platformId , @RequestBody @Valid Type type){
        typeService.addType(platformId ,type);
        return ResponseEntity.ok().body("type added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateType(@PathVariable Integer id ,@RequestBody@Valid Type type){
        typeService.updateType(id,type);
        return ResponseEntity.ok().body("type updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteType(@PathVariable Integer id){
        typeService.deleteType(id);
        return ResponseEntity.ok().body("type deleted successfully");
    }
}