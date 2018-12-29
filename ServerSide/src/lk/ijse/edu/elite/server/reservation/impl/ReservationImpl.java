package lk.ijse.edu.elite.server.reservation.impl;

import java.util.HashMap;

public class ReservationImpl<T> {
    private static class ResMap<T> {
        private T service;
        private boolean check;

        public ResMap(T service, boolean check) {
            this.service = service;
            this.check = check;
        }
    }

    private HashMap<Object, ResMap<T>> map = new HashMap<>();

    public boolean reserve(Object key, T service, boolean check) {
        if (map.containsKey(key)) {
            return map.get(key).service == service;
        } else {
            map.put(key, new ResMap<T>(service, check));
            return true;
        }
    }

    public boolean release(Object key, T service) {
        if (map.containsKey(key) && map.get(key).service == service) {
            map.remove(key);
            return true;
        }
        return false;
    }

    public boolean checkState(Object key, T service) {
        if (map.containsKey(key) && map.get(key).service == service) {
            return map.get(key).check;
        }
        return false;
    }
}
