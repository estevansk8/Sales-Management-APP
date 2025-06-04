package org.sales.management.core.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.sales.management.core.data.local.datastore.createDataStore
import org.sales.management.core.data.local.datastore.dataStoreFileName

fun createDataStore(context: Context): DataStore<Preferences> = createDataStore(
    producePath = {context.filesDir.resolve(dataStoreFileName).absolutePath}
)
