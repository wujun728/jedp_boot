/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Caratacus
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.baomidou.hibernateplus.converter;

import com.baomidou.hibernateplus.converter.type.IEnum;

/**
 * IEnum到String转换器
 *
 * Created by liyd on 8/10/14.
 */
public class EnumStringConverter extends AbstractTypeConverter {

    public EnumStringConverter(Class<?> sourceClass, Class<?> targetClass) {
        super(sourceClass, targetClass);
    }

    public Object convert(Class<?> actualSourceClass, Class<?> actualTargetClass, Object value) {

        if (value == null) {
            return null;
        }
        if (IEnum.class.isAssignableFrom(this.getSourceTypeClass())
                && this.getSourceTypeClass().isAssignableFrom(value.getClass())
                && this.getTargetTypeClass().equals(String.class)) {

            return ((IEnum) value).getCode();
        } else if (String.class.equals(this.getSourceTypeClass()) && this.getSourceTypeClass().equals(value.getClass())
                && IEnum.class.isAssignableFrom(this.getTargetTypeClass())) {

            IEnum[] iEnums = (IEnum[]) actualTargetClass.getEnumConstants();
            for (IEnum iEnum : iEnums) {
                if (iEnum.getCode().equals(String.valueOf(value))) {
                    return iEnum;
                }
            }
        }
        return value;
    }
}
