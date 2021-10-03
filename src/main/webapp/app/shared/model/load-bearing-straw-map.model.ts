export interface ILoadBearingStrawMap {
  id?: number;
  name?: string | null;
  latitude?: number;
  longitude?: number;
}

export class LoadBearingStrawMap implements ILoadBearingStrawMap {
  constructor(public id?: number, public name?: string | null, public latitude?: number, public longitude?: number) {}
}
