package com.mobile.projectapi.utils

import android.app.Application
import com.mobile.projectapi.api.Api
import com.mobile.projectapi.api.Client
import com.mobile.projectapi.database.ProjectApiDatabase
import com.mobile.projectapi.factory.ProjectApiViewModelFactory
import com.mobile.projectapi.repository.ProjectApiRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class ProjectApiApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ProjectApiApplication))

        bind<Api>() with singleton { Client.instance }
        bind() from singleton { ProjectApiDatabase(instance()) }
        bind() from singleton { ProjectApiViewModelFactory(instance()) }
        bind() from singleton { ProjectApiRepository(instance(), instance()) }
    }
}