package a.itcast.mobileplayer95.http;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 作者：Magic on 2017/5/19 18:34
 * 邮箱：bonian1852@163.com
 */

public abstract class BaseCallBack<T> {

    /**
     * 15-32行 都是去获取BaseCallBack<T>的泛型的
     */
    Type type;

    static Type getSuperclassTypeParameter(Class<?> subclass)
    {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }


    /**
     * 只要创建这个BaseCallBack对象就会给type赋值,这个值就是这个<T>泛型
     */
    public BaseCallBack()
    {
        type = getSuperclassTypeParameter(getClass());
    }

    public abstract void onFailure(int code,Exception e);



    public abstract void onSuccess(T t);


}
