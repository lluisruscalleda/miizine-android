package com.example.data.entity.mapper;

import com.example.data.entity.PillEntity;
import com.example.domain.model.PillDomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PillDataDomainMapper {

  @Inject
  public PillDataDomainMapper() {
  }

  public PillDomain transform(PillEntity pillEntity) {
    return PillDomain.create(pillEntity.getId(), pillEntity.getName(), pillEntity.getDescription(), pillEntity.getCategorie(), pillEntity.getImage());
  }

  public List<PillDomain> transform(Collection<PillEntity> pillEntityCollection) {
    List<PillDomain> pillDomainList = new ArrayList<>(pillEntityCollection.size());
    PillDomain pillDomain;
    for (PillEntity pillEntity : pillEntityCollection) {
      pillDomain = transform(pillEntity);
      if (pillDomain != null) {
        pillDomainList.add(pillDomain);
      }
    }

    return pillDomainList;
  }

  public PillEntity transformToData(PillDomain pillDomain) {
    return PillEntity.create(pillDomain.getId(), pillDomain.getName(), pillDomain.getDescription(), pillDomain.getCategorie(), pillDomain.getImage());
  }

  public List<PillEntity> transformToData(Collection<PillDomain> pillDomainCollection) {
    List<PillEntity> pillEntityList = new ArrayList<>(pillDomainCollection.size());
    PillEntity pillEntity;
    for (PillDomain pillDomain : pillDomainCollection) {
      pillEntity = transformToData(pillDomain);
      if (pillEntity != null) {
        pillEntityList.add(pillEntity);
      }
    }

    return pillEntityList;
  }
}
