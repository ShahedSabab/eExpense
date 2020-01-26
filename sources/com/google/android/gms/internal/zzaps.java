package com.google.android.gms.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class zzaps {
    private final Map<Type, zzaou<?>> bop;

    public zzaps(Map<Type, zzaou<?>> map) {
        this.bop = map;
    }

    private <T> zzapx<T> zzc(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    return new TreeSet();
                }
            } : EnumSet.class.isAssignableFrom(cls) ? new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    if (type instanceof ParameterizedType) {
                        Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                        if (type instanceof Class) {
                            return EnumSet.noneOf((Class) type);
                        }
                        String str = "Invalid EnumSet type: ";
                        String valueOf = String.valueOf(type.toString());
                        throw new zzaoz(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    }
                    String str2 = "Invalid EnumSet type: ";
                    String valueOf2 = String.valueOf(type.toString());
                    throw new zzaoz(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
                }
            } : Set.class.isAssignableFrom(cls) ? new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    return new LinkedHashSet();
                }
            } : Queue.class.isAssignableFrom(cls) ? new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    return new LinkedList();
                }
            } : new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    return new ArrayList();
                }
            };
        }
        if (Map.class.isAssignableFrom(cls)) {
            return SortedMap.class.isAssignableFrom(cls) ? new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    return new TreeMap();
                }
            } : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(zzaqo.zzl(((ParameterizedType) type).getActualTypeArguments()[0]).mo10723bB())) ? new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    return new zzapw();
                }
            } : new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    return new LinkedHashMap();
                }
            };
        }
        return null;
    }

    private <T> zzapx<T> zzd(final Type type, final Class<? super T> cls) {
        return new zzapx<T>() {
            private final zzaqa boS = zzaqa.m72bo();

            /* renamed from: bj */
            public T mo10557bj() {
                try {
                    return this.boS.zzf(cls);
                } catch (Exception e) {
                    String valueOf = String.valueOf(type);
                    throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 116).append("Unable to invoke no-args constructor for ").append(valueOf).append(". ").append("Register an InstanceCreator with Gson for this type may fix this problem.").toString(), e);
                }
            }
        };
    }

    private <T> zzapx<T> zzl(Class<? super T> cls) {
        try {
            final Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (InstantiationException e) {
                        String valueOf = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to invoke ").append(valueOf).append(" with no args").toString(), e);
                    } catch (InvocationTargetException e2) {
                        String valueOf2 = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf2).length() + 30).append("Failed to invoke ").append(valueOf2).append(" with no args").toString(), e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public String toString() {
        return this.bop.toString();
    }

    public <T> zzapx<T> zzb(zzaqo<T> zzaqo) {
        final Type bC = zzaqo.mo10724bC();
        Class bB = zzaqo.mo10723bB();
        final zzaou zzaou = (zzaou) this.bop.get(bC);
        if (zzaou != null) {
            return new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    return zzaou.zza(bC);
                }
            };
        }
        final zzaou zzaou2 = (zzaou) this.bop.get(bB);
        if (zzaou2 != null) {
            return new zzapx<T>() {
                /* renamed from: bj */
                public T mo10557bj() {
                    return zzaou2.zza(bC);
                }
            };
        }
        zzapx<T> zzl = zzl(bB);
        if (zzl != null) {
            return zzl;
        }
        zzapx<T> zzc = zzc(bC, bB);
        return zzc == null ? zzd(bC, bB) : zzc;
    }
}
