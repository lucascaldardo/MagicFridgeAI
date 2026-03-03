package com.example.MagicFridgeAI.service;

import com.example.MagicFridgeAI.model.FooditemModel;
import com.example.MagicFridgeAI.repository.FooditemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class FooditemService {

    private FooditemRepository fooditemRepository;

    public FooditemService(FooditemRepository fooditemRepository) {
        this.fooditemRepository = fooditemRepository;
    }

    public FooditemModel salvar(FooditemModel foodItem){
        return fooditemRepository.save(foodItem);
    }

    public List<FooditemModel> listar(){
        return fooditemRepository.findAll();
    }

    public Optional<FooditemModel> buscarPorId(Long id){
        return fooditemRepository.findById(id);
    }

    //alterar
    public FooditemModel atualizarPorId(FooditemModel fooditemModel){
        return fooditemRepository.save(fooditemModel);
    }

    //deletar
    public  void deletarPorId(Long id) {
        fooditemRepository.deleteById(id);
    }
}
