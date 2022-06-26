package com.yichen.casetest.problem.bot;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/26 9:49
 * @describe
 */
public class RequestCommon<T> implements Serializable {
    private static final long serialVersionUID = 2100582477125476399L;

    private String proId;

    private Integer tenantId;

    private String channel;

    private String deviceType;

    private String version;

    private long t = System.currentTimeMillis();

    private String traceId = UUID.randomUUID().toString();

    private double lng;

    private double lat;

    @Valid
    private T data;

    public RequestCommon() {
    }

    public String getProId() {
        return this.proId;
    }

    public Integer getTenantId() {
        return this.tenantId;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getVersion() {
        return this.version;
    }

    public long getT() {
        return this.t;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public double getLng() {
        return this.lng;
    }

    public double getLat() {
        return this.lat;
    }

    public T getData() {
        return this.data;
    }

    public void setProId(final String proId) {
        this.proId = proId;
    }

    public void setTenantId(final Integer tenantId) {
        this.tenantId = tenantId;
    }

    public void setChannel(final String channel) {
        this.channel = channel;
    }

    public void setDeviceType(final String deviceType) {
        this.deviceType = deviceType;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public void setT(final long t) {
        this.t = t;
    }

    public void setTraceId(final String traceId) {
        this.traceId = traceId;
    }

    public void setLng(final double lng) {
        this.lng = lng;
    }

    public void setLat(final double lat) {
        this.lat = lat;
    }

    public void setData(final T data) {
        this.data = data;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RequestCommon)) {
            return false;
        } else {
            RequestCommon<?> other = (RequestCommon)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label107: {
                    Object this$proId = this.getProId();
                    Object other$proId = other.getProId();
                    if (this$proId == null) {
                        if (other$proId == null) {
                            break label107;
                        }
                    } else if (this$proId.equals(other$proId)) {
                        break label107;
                    }

                    return false;
                }

                Object this$tenantId = this.getTenantId();
                Object other$tenantId = other.getTenantId();
                if (this$tenantId == null) {
                    if (other$tenantId != null) {
                        return false;
                    }
                } else if (!this$tenantId.equals(other$tenantId)) {
                    return false;
                }

                Object this$channel = this.getChannel();
                Object other$channel = other.getChannel();
                if (this$channel == null) {
                    if (other$channel != null) {
                        return false;
                    }
                } else if (!this$channel.equals(other$channel)) {
                    return false;
                }

                label86: {
                    Object this$deviceType = this.getDeviceType();
                    Object other$deviceType = other.getDeviceType();
                    if (this$deviceType == null) {
                        if (other$deviceType == null) {
                            break label86;
                        }
                    } else if (this$deviceType.equals(other$deviceType)) {
                        break label86;
                    }

                    return false;
                }

                label79: {
                    Object this$version = this.getVersion();
                    Object other$version = other.getVersion();
                    if (this$version == null) {
                        if (other$version == null) {
                            break label79;
                        }
                    } else if (this$version.equals(other$version)) {
                        break label79;
                    }

                    return false;
                }

                if (this.getT() != other.getT()) {
                    return false;
                } else {
                    label71: {
                        Object this$traceId = this.getTraceId();
                        Object other$traceId = other.getTraceId();
                        if (this$traceId == null) {
                            if (other$traceId == null) {
                                break label71;
                            }
                        } else if (this$traceId.equals(other$traceId)) {
                            break label71;
                        }

                        return false;
                    }

                    if (Double.compare(this.getLng(), other.getLng()) != 0) {
                        return false;
                    } else if (Double.compare(this.getLat(), other.getLat()) != 0) {
                        return false;
                    } else {
                        Object this$data = this.getData();
                        Object other$data = other.getData();
                        if (this$data == null) {
                            if (other$data == null) {
                                return true;
                            }
                        } else if (this$data.equals(other$data)) {
                            return true;
                        }

                        return false;
                    }
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RequestCommon;
    }

    @Override
    public int hashCode() {
        int PRIME = 1;
        int result = 1;
        Object $proId = this.getProId();
        result = result * 59 + ($proId == null ? 43 : $proId.hashCode());
        Object $tenantId = this.getTenantId();
        result = result * 59 + ($tenantId == null ? 43 : $tenantId.hashCode());
        Object $channel = this.getChannel();
        result = result * 59 + ($channel == null ? 43 : $channel.hashCode());
        Object $deviceType = this.getDeviceType();
        result = result * 59 + ($deviceType == null ? 43 : $deviceType.hashCode());
        Object $version = this.getVersion();
        result = result * 59 + ($version == null ? 43 : $version.hashCode());
        long $t = this.getT();
        result = result * 59 + (int)($t >>> 32 ^ $t);
        Object $traceId = this.getTraceId();
        result = result * 59 + ($traceId == null ? 43 : $traceId.hashCode());
        long $lng = Double.doubleToLongBits(this.getLng());
        result = result * 59 + (int)($lng >>> 32 ^ $lng);
        long $lat = Double.doubleToLongBits(this.getLat());
        result = result * 59 + (int)($lat >>> 32 ^ $lat);
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RequestCommon(proId=" + this.getProId() + ", tenantId=" + this.getTenantId() + ", channel=" + this.getChannel() + ", deviceType=" + this.getDeviceType() + ", version=" + this.getVersion() + ", t=" + this.getT() + ", traceId=" + this.getTraceId() + ", lng=" + this.getLng() + ", lat=" + this.getLat() + ", data=" + this.getData() + ")";
    }
}
