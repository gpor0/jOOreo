package com.github.gpor0.jooreo.filters;

import com.github.gpor0.jooreo.UUIDv7;
import com.github.gpor0.jooreo.filters.defaults.OnInsertDefaultFilter;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.TableRecord;

import static com.github.gpor0.jooreo.Jooreo.getField;

/**
 * Author: gpor0
 */
public class RandomUUIDCreateFilter extends OnInsertDefaultFilter {

    @Override
    public <T extends TableRecord> int filter(DSLContext dsl, T r) {

        Field idField = getField(r, "id");
        if (idField != null) {
            r.setValue(idField, UUIDv7.randomUUID());
        }

        return super.filter(dsl, r);
    }
}
