package com.mmt.resort.service;

import com.mmt.resort.model.Villa;

public interface VillaService {

    Villa create(Villa villa);

    Villa update(Long id, Villa villa);

    void delete(Long id);

}
