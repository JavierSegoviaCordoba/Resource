package com.javiersc.app

import com.javiersc.app.data.repo.GitHubRepo
import com.javiersc.app.data.repo.models.Error
import com.javiersc.app.data.repo.models.User
import com.javiersc.resource.Resource
import kotlinx.coroutines.flow.Flow

class GitHubViewModel(gitHubRepo: GitHubRepo) {

    val usersRes: Flow<Resource<List<User>, Error>> = gitHubRepo.getUsers()
    val signInRes: Flow<Resource<String, Error>> = gitHubRepo.signIn()
}
