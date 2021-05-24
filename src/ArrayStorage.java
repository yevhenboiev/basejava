import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        Arrays.fill(storage, 0, storage.length, null);
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) { return null; }

    void delete(String uuid) {
        storage[Integer.parseInt(uuid)] = null;
        for (int i = Integer.parseInt(uuid); i < storage.length-1; i++) {
            storage[i - 1] = storage[i];
            storage[i] = null;
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
