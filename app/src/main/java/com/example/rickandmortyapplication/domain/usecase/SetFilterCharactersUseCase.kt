package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.model.CharacterResponse
import com.example.rickandmortyapplication.domain.model.FilterData
import com.example.rickandmortyapplication.domain.model.Info
import com.example.rickandmortyapplication.domain.repository.CharacterDataBaseRepository
import com.example.rickandmortyapplication.domain.repository.FilterCharacterRepository
import com.example.rickandmortyapplication.domain.repository.FilterRepository
import com.example.rickandmortyapplication.utils.ResultProcessing
import retrofit2.HttpException
import java.io.IOException

class SetFilterCharactersUseCase(
    private val filterCharacterRepository: FilterCharacterRepository,
    private val filterRepository: FilterRepository,
    private val characterDataBaseRepository: CharacterDataBaseRepository
) {
    suspend fun execute(page: Int, filter: FilterData): ResultProcessing<CharacterResponse> {
        val filterApply = filter
        val filterNew = filterApply.copy(isApply = false)
        filterRepository.saveFilters(filterNew)

        return try {
            val data = filterCharacterRepository.getCharacters(page, filterApply)
            characterDataBaseRepository.insertAll(*data.character.toTypedArray())
            ResultProcessing.Success(data)

        } catch (e: HttpException) {
            val message = when (e.code()) {
                400 -> "Неверный запрос"
                404 -> "Данные  не найдены"
                429 -> "Слишком много запросов"
                500 -> "Ошибка сервера"
                else -> "Неизвестная ошибка"
            }
            ResultProcessing.Error(message)
        } catch (e: Exception) {
            ResultProcessing.Error("Ошибка ${e.message}")
        }
    }
}