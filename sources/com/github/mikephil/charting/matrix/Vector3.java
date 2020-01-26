package com.github.mikephil.charting.matrix;

public final class Vector3 {
    public static final Vector3 UNIT_X = new Vector3(1.0f, 0.0f, 0.0f);
    public static final Vector3 UNIT_Y = new Vector3(0.0f, 1.0f, 0.0f);
    public static final Vector3 UNIT_Z = new Vector3(0.0f, 0.0f, 1.0f);
    public static final Vector3 ZERO = new Vector3(0.0f, 0.0f, 0.0f);

    /* renamed from: x */
    public float f62x;

    /* renamed from: y */
    public float f63y;

    /* renamed from: z */
    public float f64z;

    public Vector3() {
    }

    public Vector3(float[] array) {
        set(array[0], array[1], array[2]);
    }

    public Vector3(float xValue, float yValue, float zValue) {
        set(xValue, yValue, zValue);
    }

    public Vector3(Vector3 other) {
        set(other);
    }

    public final void add(Vector3 other) {
        this.f62x += other.f62x;
        this.f63y += other.f63y;
        this.f64z += other.f64z;
    }

    public final void add(float otherX, float otherY, float otherZ) {
        this.f62x += otherX;
        this.f63y += otherY;
        this.f64z += otherZ;
    }

    public final void subtract(Vector3 other) {
        this.f62x -= other.f62x;
        this.f63y -= other.f63y;
        this.f64z -= other.f64z;
    }

    public final void subtractMultiple(Vector3 other, float multiplicator) {
        this.f62x -= other.f62x * multiplicator;
        this.f63y -= other.f63y * multiplicator;
        this.f64z -= other.f64z * multiplicator;
    }

    public final void multiply(float magnitude) {
        this.f62x *= magnitude;
        this.f63y *= magnitude;
        this.f64z *= magnitude;
    }

    public final void multiply(Vector3 other) {
        this.f62x *= other.f62x;
        this.f63y *= other.f63y;
        this.f64z *= other.f64z;
    }

    public final void divide(float magnitude) {
        if (magnitude != 0.0f) {
            this.f62x /= magnitude;
            this.f63y /= magnitude;
            this.f64z /= magnitude;
        }
    }

    public final void set(Vector3 other) {
        this.f62x = other.f62x;
        this.f63y = other.f63y;
        this.f64z = other.f64z;
    }

    public final void set(float xValue, float yValue, float zValue) {
        this.f62x = xValue;
        this.f63y = yValue;
        this.f64z = zValue;
    }

    public final float dot(Vector3 other) {
        return (this.f62x * other.f62x) + (this.f63y * other.f63y) + (this.f64z * other.f64z);
    }

    public final Vector3 cross(Vector3 other) {
        return new Vector3((this.f63y * other.f64z) - (this.f64z * other.f63y), (this.f64z * other.f62x) - (this.f62x * other.f64z), (this.f62x * other.f63y) - (this.f63y * other.f62x));
    }

    public final float length() {
        return (float) Math.sqrt((double) length2());
    }

    public final float length2() {
        return (this.f62x * this.f62x) + (this.f63y * this.f63y) + (this.f64z * this.f64z);
    }

    public final float distance2(Vector3 other) {
        float dx = this.f62x - other.f62x;
        float dy = this.f63y - other.f63y;
        float dz = this.f64z - other.f64z;
        return (dx * dx) + (dy * dy) + (dz * dz);
    }

    public final float normalize() {
        float magnitude = length();
        if (magnitude != 0.0f) {
            this.f62x /= magnitude;
            this.f63y /= magnitude;
            this.f64z /= magnitude;
        }
        return magnitude;
    }

    public final void zero() {
        set(0.0f, 0.0f, 0.0f);
    }

    public final boolean pointsInSameDirection(Vector3 other) {
        return dot(other) > 0.0f;
    }
}
