package com.example.rickandmortyapplication.domain.usecase

import com.example.rickandmortyapplication.domain.model.CharacterData
import com.example.rickandmortyapplication.domain.model.CharacterResponse
import com.example.rickandmortyapplication.domain.repository.CharacterRepository
import com.example.rickandmortyapplication.utils.ResultProcessing
import retrofit2.HttpException

class GetCharacterUseCase(
    val characterRepository: CharacterRepository
) {
    suspend fun execute(id: Int): ResultProcessing<CharacterData> {

        return try {
            ResultProcessing.Success(characterRepository.getCharacter(id))
        } catch (e: HttpException) {
            val message = when (e.code()) {
                400 -> "Неверный запрос"//resourcesManager.getString(R.string.invalid_request, e.code().toString())
                404 -> "Данные  не найдены"
                429 -> "Слишком много запросов"
                500 -> "Ошибка сервера" //resourcesManager.getString(R.string.server_error, e.code().toString())
                else -> "Неизвестная ошибка"// resourcesManager.getString(R.string.unknown_error)
            }
            ResultProcessing.Error(message)
        } catch (e: Exception) {
            ResultProcessing.Error("Ошибка ${e.message}")
        }

    }
}