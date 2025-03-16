package cleancode.studycafe.tobetest.provider;

import cleancode.studycafe.tobetest.model.pass.locker.StudyCafeLockerPasses;

public interface LockerPassProvider {
    StudyCafeLockerPasses getLockerPasses();

    StudyCafeLockerPasses getLockerPasses(String path);

}
