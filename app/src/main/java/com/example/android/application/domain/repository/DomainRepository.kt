package com.example.android.application.domain.repository

import com.example.android.application.data.remote.dto.Response


interface DomainRepository {
    suspend fun getProp() : Response
}