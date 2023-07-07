import { TestBed, inject } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import { AuthService } from "./auth.service";
import { User } from "../types/user";
import { environment } from "src/environments/environment";

describe("AuthService", () => {
  let authService: AuthService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthService],
    });

    authService = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it("should be created", () => {
    expect(authService).toBeTruthy();
  });

  describe("login", () => {
    it("should make a POST request to login", () => {
      const user: User = {
        username: "admin.user",
        password: "12345",
        role: "ADMIN",
      };

      authService.login(user).subscribe((result) => {
        expect(result).not.toBeNull();
      });

      const req = httpMock.expectOne(`${environment.apiUrl}/authenticate`);
      expect(req.request.method).toBe("POST");
      expect(req.request.body).toEqual(user);

      req.flush(user);
    });
  });

  describe("getToken", () => {
    it("should get the token from localStorage", () => {
      const token = "testtoken";
      spyOn(localStorage, "getItem").and.returnValue(token);

      const result = authService.getToken();

      expect(localStorage.getItem).toHaveBeenCalledWith("token");
      expect(result).toEqual(token);
    });
  });

  describe("getUsers", () => {
    it("should make a GET request to retrieve users", () => {
      const users: User[] = [
        { username: "user1", password: "password1", role: "ADMIN" },
        { username: "user2", password: "password2", role: "ADMIN" },
      ];

      authService.getUsers().subscribe((result) => {
        expect(result).toEqual(users);
      });

      const req = httpMock.expectOne(`${environment.apiUrl}/fetchusers`);
      expect(req.request.method).toBe("GET");

      req.flush(users);
    });
  });

  describe("createUser", () => {
    it("should make a POST request to create a user", () => {
      const user: User = {
        username: "testuser",
        password: "testpassword",
        role: "ADMIN",
      };
      const response: User = {
        username: "testuser",
        password: "testpassword",
        role: "ADMIN",
      };

      authService.createUser(user).subscribe((result) => {
        expect(result).toEqual(response);
      });

      const req = httpMock.expectOne(`${environment.apiUrl}/user`);
      expect(req.request.method).toBe("POST");
      expect(req.request.body).toEqual(user);

      req.flush(response);
    });
  });
});
