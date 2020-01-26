package com.google.android.gms.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class zzapr {
    static final Type[] boI = new Type[0];

    private static final class zza implements Serializable, GenericArrayType {
        private final Type boJ;

        public zza(Type type) {
            this.boJ = zzapr.zze(type);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && zzapr.zza((Type) this, (Type) (GenericArrayType) obj);
        }

        public Type getGenericComponentType() {
            return this.boJ;
        }

        public int hashCode() {
            return this.boJ.hashCode();
        }

        public String toString() {
            return String.valueOf(zzapr.zzg(this.boJ)).concat("[]");
        }
    }

    private static final class zzb implements Serializable, ParameterizedType {
        private final Type boK;
        private final Type boL;
        private final Type[] boM;

        public zzb(Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                zzapq.zzbt(type != null || (Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null));
            }
            this.boK = type == null ? null : zzapr.zze(type);
            this.boL = zzapr.zze(type2);
            this.boM = (Type[]) typeArr.clone();
            for (int i = 0; i < this.boM.length; i++) {
                zzapq.zzy(this.boM[i]);
                zzapr.zzi(this.boM[i]);
                this.boM[i] = zzapr.zze(this.boM[i]);
            }
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && zzapr.zza((Type) this, (Type) (ParameterizedType) obj);
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.boM.clone();
        }

        public Type getOwnerType() {
            return this.boK;
        }

        public Type getRawType() {
            return this.boL;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.boM) ^ this.boL.hashCode()) ^ zzapr.zzco(this.boK);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((this.boM.length + 1) * 30);
            sb.append(zzapr.zzg(this.boL));
            if (this.boM.length == 0) {
                return sb.toString();
            }
            sb.append("<").append(zzapr.zzg(this.boM[0]));
            for (int i = 1; i < this.boM.length; i++) {
                sb.append(", ").append(zzapr.zzg(this.boM[i]));
            }
            return sb.append(">").toString();
        }
    }

    private static final class zzc implements Serializable, WildcardType {
        private final Type boN;
        private final Type boO;

        public zzc(Type[] typeArr, Type[] typeArr2) {
            boolean z = true;
            zzapq.zzbt(typeArr2.length <= 1);
            zzapq.zzbt(typeArr.length == 1);
            if (typeArr2.length == 1) {
                zzapq.zzy(typeArr2[0]);
                zzapr.zzi(typeArr2[0]);
                if (typeArr[0] != Object.class) {
                    z = false;
                }
                zzapq.zzbt(z);
                this.boO = zzapr.zze(typeArr2[0]);
                this.boN = Object.class;
                return;
            }
            zzapq.zzy(typeArr[0]);
            zzapr.zzi(typeArr[0]);
            this.boO = null;
            this.boN = zzapr.zze(typeArr[0]);
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && zzapr.zza((Type) this, (Type) (WildcardType) obj);
        }

        public Type[] getLowerBounds() {
            if (this.boO == null) {
                return zzapr.boI;
            }
            return new Type[]{this.boO};
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.boN};
        }

        public int hashCode() {
            return (this.boO != null ? this.boO.hashCode() + 31 : 1) ^ (this.boN.hashCode() + 31);
        }

        public String toString() {
            if (this.boO != null) {
                String str = "? super ";
                String valueOf = String.valueOf(zzapr.zzg(this.boO));
                return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
            } else if (this.boN == Object.class) {
                return "?";
            } else {
                String str2 = "? extends ";
                String valueOf2 = String.valueOf(zzapr.zzg(this.boN));
                return valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2);
            }
        }
    }

    static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int zza(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> zza(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    public static ParameterizedType zza(Type type, Type type2, Type... typeArr) {
        return new zzb(type, type2, typeArr);
    }

    public static Type zza(Type type, Class<?> cls) {
        Type zzb2 = zzb(type, cls, Collection.class);
        if (zzb2 instanceof WildcardType) {
            zzb2 = ((WildcardType) zzb2).getUpperBounds()[0];
        }
        return zzb2 instanceof ParameterizedType ? ((ParameterizedType) zzb2).getActualTypeArguments()[0] : Object.class;
    }

    static Type zza(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(interfaces[i])) {
                    return zza(cls.getGenericInterfaces()[i], interfaces[i], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return zza(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public static Type zza(Type type, Class<?> cls, Type type2) {
        while (true) {
            Type type3 = type2;
            if (type3 instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) type3;
                type2 = zza(type, cls, typeVariable);
                if (type2 == typeVariable) {
                    return type2;
                }
            } else if ((type3 instanceof Class) && ((Class) type3).isArray()) {
                Class cls2 = (Class) type3;
                Class componentType = cls2.getComponentType();
                Type zza2 = zza(type, cls, (Type) componentType);
                return componentType != zza2 ? zzb(zza2) : cls2;
            } else if (type3 instanceof GenericArrayType) {
                GenericArrayType genericArrayType = (GenericArrayType) type3;
                Type genericComponentType = genericArrayType.getGenericComponentType();
                Type zza3 = zza(type, cls, genericComponentType);
                return genericComponentType != zza3 ? zzb(zza3) : genericArrayType;
            } else if (type3 instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type3;
                Type ownerType = parameterizedType.getOwnerType();
                Type zza4 = zza(type, cls, ownerType);
                boolean z = zza4 != ownerType;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                int length = actualTypeArguments.length;
                boolean z2 = z;
                Type[] typeArr = actualTypeArguments;
                for (int i = 0; i < length; i++) {
                    Type zza5 = zza(type, cls, typeArr[i]);
                    if (zza5 != typeArr[i]) {
                        if (!z2) {
                            typeArr = (Type[]) typeArr.clone();
                            z2 = true;
                        }
                        typeArr[i] = zza5;
                    }
                }
                return z2 ? zza(zza4, parameterizedType.getRawType(), typeArr) : parameterizedType;
            } else if (!(type3 instanceof WildcardType)) {
                return type3;
            } else {
                WildcardType wildcardType = (WildcardType) type3;
                Type[] lowerBounds = wildcardType.getLowerBounds();
                Type[] upperBounds = wildcardType.getUpperBounds();
                if (lowerBounds.length == 1) {
                    Type zza6 = zza(type, cls, lowerBounds[0]);
                    return zza6 != lowerBounds[0] ? zzd(zza6) : wildcardType;
                } else if (upperBounds.length != 1) {
                    return wildcardType;
                } else {
                    Type zza7 = zza(type, cls, upperBounds[0]);
                    return zza7 != upperBounds[0] ? zzc(zza7) : wildcardType;
                }
            }
        }
    }

    static Type zza(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class zza2 = zza(typeVariable);
        if (zza2 == null) {
            return typeVariable;
        }
        Type zza3 = zza(type, cls, zza2);
        if (!(zza3 instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) zza3).getActualTypeArguments()[zza((Object[]) zza2.getTypeParameters(), (Object) typeVariable)];
    }

    public static boolean zza(Type type, Type type2) {
        boolean z = true;
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            if (!equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || !Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments())) {
                z = false;
            }
            return z;
        } else if (type instanceof GenericArrayType) {
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return zza(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) || !Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds())) {
                z = false;
            }
            return z;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (typeVariable.getGenericDeclaration() != typeVariable2.getGenericDeclaration() || !typeVariable.getName().equals(typeVariable2.getName())) {
                z = false;
            }
            return z;
        }
    }

    public static GenericArrayType zzb(Type type) {
        return new zza(type);
    }

    static Type zzb(Type type, Class<?> cls, Class<?> cls2) {
        zzapq.zzbt(cls2.isAssignableFrom(cls));
        return zza(type, cls, zza(type, cls, cls2));
    }

    public static Type[] zzb(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type zzb2 = zzb(type, cls, Map.class);
        if (zzb2 instanceof ParameterizedType) {
            return ((ParameterizedType) zzb2).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    public static WildcardType zzc(Type type) {
        return new zzc(new Type[]{type}, boI);
    }

    /* access modifiers changed from: private */
    public static int zzco(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static WildcardType zzd(Type type) {
        return new zzc(new Type[]{Object.class}, new Type[]{type});
    }

    public static Type zze(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return (Type) (cls.isArray() ? new zza(zze(cls.getComponentType())) : cls);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new zzb(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new zza(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new zzc(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static Class<?> zzf(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            zzapq.zzbt(rawType instanceof Class);
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(zzf(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return zzf(((WildcardType) type).getUpperBounds()[0]);
            }
            String name = type == null ? "null" : type.getClass().getName();
            String valueOf = String.valueOf("Expected a Class, ParameterizedType, or GenericArrayType, but <");
            String valueOf2 = String.valueOf(type);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 13 + String.valueOf(valueOf2).length() + String.valueOf(name).length()).append(valueOf).append(valueOf2).append("> is of type ").append(name).toString());
        }
    }

    public static String zzg(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static Type zzh(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    /* access modifiers changed from: private */
    public static void zzi(Type type) {
        zzapq.zzbt(!(type instanceof Class) || !((Class) type).isPrimitive());
    }
}
