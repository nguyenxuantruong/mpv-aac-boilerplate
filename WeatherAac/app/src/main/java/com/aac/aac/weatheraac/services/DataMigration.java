package com.aac.aac.weatheraac.services;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class DataMigration implements RealmMigration {

    private RealmSchema schema;

    public static final int REALM_SCHEMA_VERSION = 0;

    @Override
    public void migrate(@NonNull DynamicRealm realm, long oldVersion, long newVersion) {
        Logger.d("Realm migration call. Old version: " + oldVersion + ", new version: " + newVersion);

        // Migrate version 0 to 1
        if (oldVersion == 0) {
            Logger.d("Migration from 0 to 1");

            // handle migrate from 0 to 1.
            // migrateToV1();
        }
    }

    /**
     * this is for testing - migrate to v1
     * add age field (Integer type) into User object
     */
    private void migrateToV1() {
//        final RealmObjectSchema userSchema = schema.get("User");
//        userSchema.addField("age", int.class);
    }
}
