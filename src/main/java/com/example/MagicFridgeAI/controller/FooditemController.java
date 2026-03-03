package com.example.MagicFridgeAI.controller;

import com.example.MagicFridgeAI.model.FooditemModel;
import com.example.MagicFridgeAI.service.FooditemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/food")
public class FooditemController {
    private FooditemService fooditemService;

    public FooditemController(FooditemService fooditemService) {
        this.fooditemService = fooditemService;
    }

    //Post

    @PostMapping("/criar")
    public ResponseEntity<FooditemModel> criar(@RequestBody FooditemModel fooditemModel){
        FooditemModel criado = fooditemService.salvar(fooditemModel);
        return ResponseEntity.ok(criado);
    }

    //Get
    @GetMapping("/listar")
    public ResponseEntity<List<FooditemModel>>listar(){
        List<FooditemModel> listados = fooditemService.listar();
        return ResponseEntity.ok(listados);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<FooditemModel> atualizarPorId(@RequestBody FooditemModel fooditemModel, @PathVariable Long id) {
        return fooditemService.buscarPorId(id)
                .map(itemExistente -> {
                    fooditemModel.setId(itemExistente.getId());
                    FooditemModel atualizado = fooditemService.atualizarPorId(fooditemModel);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        fooditemService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
