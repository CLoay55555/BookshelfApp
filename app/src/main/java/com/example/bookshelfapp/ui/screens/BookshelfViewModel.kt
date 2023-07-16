package com.example.bookshelfapp.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.bookshelfapp.BookShelfApplication
import com.example.bookshelfapp.ui.states.BookshelfUiState
import com.example.bookshelfapp.data.GoogleBookRepository
import com.example.bookshelfapp.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import kotlinx.coroutines.flow.update

sealed interface BookshelfDataState{
    data class Success(val books: List<Book>): BookshelfDataState
    object Error: BookshelfDataState
    object Loading: BookshelfDataState
}



class BookshelfViewModel(private val bookshelfRepository: GoogleBookRepository) : ViewModel(){


    var bookshelfDataState: BookshelfDataState by mutableStateOf(BookshelfDataState.Loading)
        private set

    private val _uiState = MutableStateFlow(BookshelfUiState())
    val uiState: StateFlow<BookshelfUiState> = _uiState


    init{
        getGoogleBooks()
    }



    fun getGoogleBooks(){
        viewModelScope.launch{
            bookshelfDataState = BookshelfDataState.Loading
            bookshelfDataState = try{
                BookshelfDataState.Success(bookshelfRepository.getGoogleBooks(uiState.value.currentSearch))
            }catch(e: IOException){
                Log.e("IOExceptionError","Caught exception:${e.message}")
                BookshelfDataState.Error
            }catch(e: HttpException){
                Log.e("HttpExceptionError","Caught exception:${e.message}")
                BookshelfDataState.Error

            }

        }
    }

    fun updateUserSearch(userS: String){
        _uiState.update{
            it.copy(
                currentSearch = userS,
                isShowingHomepage = true
            )
        }
    }

    fun updateDetailsScreenStates(book: Book){
        _uiState.update{
            it.copy(
                currentBook = book,
                isShowingHomepage = false
            )
        }
    }

    fun resetScreenStates(){
        _uiState.update{
            it.copy(
                isShowingHomepage = true
            )
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val bookshelfRepository = application.container.googleBookRepository
                BookshelfViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}